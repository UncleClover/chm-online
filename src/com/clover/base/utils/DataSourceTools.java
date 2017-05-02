package com.clover.base.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.clover.base.constants.ChmConstants;
import com.mchange.v2.c3p0.DataSources;

@SuppressWarnings("unchecked")
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
			Element root = document.getRootElement();// 获得根节点
			_default = root.getAttributeValue("default");// 获取默认数据库连接ID

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
			logger.error("数据库文件节点读取异常：" + e);
		} catch (IOException e) {
			logger.error("数据库文件读取异常：" + e);
		}
	}

	public static DataSourceTools getInstance() {
		return instance;
	}

	/**
	 * @desc 创建C3P0数据源
	 * @author zhangdq
	 * @time 2017-4-9 下午7:50:54
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

			// 不带连接池的DataSource
			DataSource unpooled = DataSources.unpooledDataSource(url, user, password);

			// 由不带连接池的DataSource创建带连接池的DataSource
			DataSource pooled = DataSources.pooledDataSource(unpooled, paramMap);

			return pooled;
		} catch (Exception e) {
			logger.error("创建C3P0数据源异常：" + e);
		}
		return null;
	}

	/**
	 * @desc 获取默认的数据库连接
	 * @author zhangdq
	 * @time 2017-5-1 下午7:15:48
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
	 * @desc 获取指定的数据库连接
	 * @author zhangdq
	 * @time 2017-5-1 下午7:14:53
	 * @param id 指定数据库ID
	 * @return DataSource
	 */
	public DataSource getDataSource(String id) {
		if (dataSourceMap.size() < 1) {
			return null;
		}

		return dataSourceMap.get(id);
	}

	/**
	 * @desc 获取指定数据库连接配置
	 * @author zhangdq
	 * @time 2017.5.2 下午23:35:00
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
