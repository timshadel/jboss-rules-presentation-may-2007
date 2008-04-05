package edu.apollogrp.jug.service.impl;

import java.util.Collection;
import java.util.List;

import org.drools.FactHandle;
import org.drools.RuleBase;
import org.drools.WorkingMemory;
import org.drools.audit.WorkingMemoryFileLogger;

import edu.apollogrp.jug.service.RulesService;

public class RulesServiceImpl implements RulesService {

	private RuleBase scriptRuleBase;

	public Collection execute(Collection objects) {
		WorkingMemory workingMemory = scriptRuleBase.newWorkingMemory();
	    WorkingMemoryFileLogger memoryAuditLogger = new WorkingMemoryFileLogger(workingMemory);
	    // Creates event.log in the working directory
	    memoryAuditLogger.setFileName("event");
	    
	    for(Object o : objects) {
	    	workingMemory.assertObject(o);
	    }

	    // Everything interesting happens here
	    workingMemory.fireAllRules();

		Collection result = workingMemory.getObjects();

		List<FactHandle> handles = workingMemory.getFactHandles();
		for(FactHandle handle : handles) {
			workingMemory.retractObject(handle);
		}
		
		// stop logging
	    memoryAuditLogger.writeToDisk();
	    
	    return result;
	}

	/* spring di */
	public void setScriptRuleBase(RuleBase scriptRuleBase) {
		this.scriptRuleBase = scriptRuleBase;
	}

}
