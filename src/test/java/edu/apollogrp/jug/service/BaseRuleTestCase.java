package edu.apollogrp.jug.service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderConfiguration;
import org.drools.rule.Package;

import edu.apollogrp.jug.service.impl.RulesServiceImpl;

public abstract class BaseRuleTestCase extends TestCase {

	protected RulesService rulesService;

	protected abstract List<String> getPackageNames();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		RulesServiceImpl rulesServiceImpl = new RulesServiceImpl();

		// Configure our rule base using the subclass's specified DRL files
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		List<Package> packages = loadDrlPackageList(getPackageNames());
		for(Package p : packages) {
			ruleBase.addPackage(p);
		}
		rulesServiceImpl.setScriptRuleBase(ruleBase);
	
		rulesService = rulesServiceImpl;
	}

	private List<Package> loadDrlPackageList(List<String> packageNames) throws Exception {
		List<Package> packages = new ArrayList<Package>();
		
		for(String name : packageNames) {
			packages.add( loadDrlPackage(name) );
		}
		
		return packages;
	}

	/** Compile DRL files into runtime rules */
	private Package loadDrlPackage(String packageName) throws Exception {
		PackageBuilderConfiguration config = new PackageBuilderConfiguration();
		config.setCompiler(PackageBuilderConfiguration.JANINO);
		PackageBuilder builder = new PackageBuilder(config);
		Reader source = new InputStreamReader(this.getClass().getResourceAsStream(packageName));
		builder.addPackageFromDrl(source);
		Package pkg = builder.getPackage();
		return pkg;
	}

}