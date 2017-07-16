package com.clover.base.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.clover.base.constants.ChmConstants;
import com.clover.base.utils.StringUtils;
import com.mchange.v2.c3p0.DataSources;

public class DataSourceTools {

	private static Logger logger = Logger.getLogger(DataSourceTools.class);

	private static DataSourceTools instance = new DataSourceTools();

	private static Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();

	private static Map<String, Map<String, String>> dataSourceParam = new HashMap<String, Map<String, String>>();

	private static String _default = "";

	static {
		SAXBuilder builder = new SAXBuilder();
		InputStream file = ClassLoader.getSystemResourceAsStream(ChmConstants.DATASOURCE_FILE_NAME);
		try {
			Document document = builder.build(file);
			Element root = document.getRootElement();// 鑾峰緱鏍硅妭鐐�
			_default = root.getAttributeValue("default");// 鑾峰彇榛樿鏁版嵁搴撹繛鎺D

			List<Element> list = root.getChildren("bean");
			for (Iterator<Element> it = list.iterator(); it.hasNext();) {
				Element element = it.next();
				String beanId = element.getAttributeValue("id");
				if (StringUtils.isEmpty(beanId)) {
					continue;
				}
				Map<String, String> properyMap = new HashMap<String, String>();
				List<Element> propertyList = element.getChildren("property");
				for (Iterator<Element> propertyIt = propertyList.iterator(); propertyIt.hasNext();) {
					Element propertyEle = propertyIt.next();
					String propertyName = propertyEle.getAttributeValue("name");
					String propertyValue = propertyEle.getAttributeValue("value");
					properyMap.put(propertyName, propertyValue);
				}
				if (!dataSourceParam.containsKey(beanId)) {
					dataSourceParam.put(beanId, properyMap);
				}
				DataSource ds = buildDataSource(properyMap);
				dataSourceMap.put(beanId, ds);
			}
		} catch (JDOMException e) {
			logger.error("鏁版嵁搴撴枃浠惰妭鐐硅鍙栧紓甯革細" + e);
		} catch (IOException e) {
			logger.error("鏁版嵁搴撴枃浠惰鍙栧紓甯革細" + e);
		}
	}

	public static DataSourceTools getInstance() {
		return instance;
	}

	/**
	 * @desc 鍒涘缓C3P0鏁版嵁婧�
	 * @author zhangdq
	 * @time 2017-4-9 涓嬪崍7:50:54
	 * @param Map
	 * @return DataSource
	 */
	private static DataSource buildDataSource(Map<String, String> paramMap) {
		String driverName = paramMap.get("driverName");
		String url = paramMap.get("url");
		String user = paramMap.get("user");
		String password = paramMap.get("password");

		paramMap.remove("driverName");
		paramMap.remove("url");
		paramMap.remove("user");
		paramMap.remove("password");

		try {
			Class.forName(driverName);

			// 涓嶅甫杩炴帴姹犵殑DataSource
			DataSource unpooled = DataSources.unpooledDataSource(url, user, password);

			// 鐢变笉甯﹁繛鎺ユ睜鐨凞ataSource鍒涘缓甯﹁繛鎺ユ睜鐨凞ataSource
			DataSource pooled = DataSources.pooledDataSource(unpooled, paramMap);

			return pooled;
		} catch (Exception e) {
			logger.error("鍒涘缓C3P0鏁版嵁婧愬紓甯革細" + e);
		}
		return null;
	}

	/**
	 * @desc 鑾峰彇榛樿鐨勬暟鎹簱杩炴帴
	 * @author zhangdq
	 * @time 2017-5-1 涓嬪崍7:15:48
	 * @param
	 * @return DataSource
	 */
	public DataSource getDataSource() {
		if (dataSourceMap.size() < 1) {
			return null;
		}

		if (dataSourceMap.size() == 1) {
			return (DataSource) dataSourceMap.values().toArray()[0];
		}

		if (StringUtils.isEmpty(_default)) {
			return null;
		}

		return getDataSource(_default);
	}

	/**
	 * @desc 鑾峰彇鎸囧畾鐨勬暟鎹簱杩炴帴
	 * @author zhangdq
	 * @time 2017-5-1 涓嬪崍7:14:53
	 * @param id 鎸囧畾鏁版嵁搴揑D
	 * @return DataSource
	 */
	public DataSource getDataSource(String id) {
		if (dataSourceMap.size() < 1) {
			return null;
		}

		return dataSourceMap.get(id);
	}

	/**
	 * @desc 鑾峰彇鎸囧畾鏁版嵁搴撹繛鎺ラ厤缃�
	 * @author zhangdq
	 * @time 2017.5.2 涓嬪崍23:35:00
	 * @param id
	 * @return
	 */
	public HashMap<String, String> getDataSourceParam(String id) {
		if (StringUtils.isEmpty(id)) {
			id = _default;
		}

		if (StringUtils.isEmpty(id)) {
			return null;
		}
		return (HashMap<String, String>) dataSourceParam.get(id);
	}
}
