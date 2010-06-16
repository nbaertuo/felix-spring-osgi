package org.ertuo.linliqin.dao.persistence.db;

import org.hsqldb.Server;
import org.hsqldb.ServerConfiguration;
import org.hsqldb.ServerConstants;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.persist.HsqlProperties;

public class HsqlServerManager extends Server {

	public static void start(String[] args) {
		String propsPath = FileUtil.canonicalOrAbsolutePath("server");
		HsqlProperties fileProps = ServerConfiguration
				.getPropertiesFromFile(propsPath);
		if (fileProps == null) {
			fileProps = new HsqlProperties();
		}
		HsqlProperties props = fileProps;
		HsqlProperties stringProps = HsqlProperties.argArrayToProps(args,
				ServerConstants.SC_KEY_PREFIX);

		if (stringProps != null) {
			if (stringProps.getErrorKeys().length != 0) {
				printHelp("server.help");
				return;
			}
			props.addProperties(stringProps);
		}
		ServerConfiguration.translateDefaultDatabaseProperty(props);

		ServerConfiguration.translateDefaultNoSystemExitProperty(props);

		Server server = new Server();

		server.setProperties(props);

		server.start();
	}

	public static void main(String args[]) {
		String[] strs = new String[] { "-database.0", "file:d:/db/human",
				"-dbname.0", "xdb" };
		HsqlServerManager.start(strs);
	}

}
