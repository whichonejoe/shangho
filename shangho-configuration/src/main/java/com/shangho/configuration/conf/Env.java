package com.shangho.configuration.conf;

import java.io.File;

public interface Env {
	public final static String SYSTEM_DEF_PATH = System.getProperty("sh.config.path").replace("\\", File.separator)
			.replace("/", File.separator);

	public final static String PROXOOL_PATH = "/initial/proxool/proxool.xml".replace("/", File.separator);
}
