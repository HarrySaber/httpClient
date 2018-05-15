package cn.starpost.tms.client.module;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.typesafe.config.Config;

import cn.starpost.config.ConfigurationListener;
import cn.starpost.config.module.ConfigurationModule;
import cn.starpost.module.AbstractIModule;
import cn.starpost.module.ModuleSpec;
import cn.starpost.tms.client.TmsClient;

public class TmsClientModule extends AbstractIModule implements ConfigurationListener {

	public static final String MODULE_ID = "cn.starpost.module.TmsClientModule";

	public static final Logger Logger = LoggerFactory.getLogger(TmsClientModule.class);

	Config config;

	/**
	 * @param id
	 */
	public TmsClientModule() {
		super(MODULE_ID);
	}

	@Override
	public void onConfiguration(Config config) {
		this.config = config;
	}

	@Override
	public Set<ModuleSpec> getDependencyModules() {
		return Sets.newHashSet(new ModuleSpec(ConfigurationModule.MODULE_ID));
	}

	@Override
	public Module getGuiceModule() {
		if (!config.hasPath("tms.url")) {
			throw new RuntimeException("Unable Find Config[tms.url]");
		}
		String endpoint = config.getString("tms.url");
		return new AbstractModule() {
			@Override
			protected void configure() {
				TmsClient tmsClient = new TmsClient(endpoint);
				bind(TmsClient.class).toInstance(tmsClient);
			}
		};
	}

}
