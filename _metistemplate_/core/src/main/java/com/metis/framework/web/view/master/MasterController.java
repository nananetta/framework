package com.metis.framework.web.view.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.konkow.framework.domain.Result;
import com.konkow.framework.domain.master.AbstractSimpleDomain;
import com.konkow.framework.domain.master.DocumentStatus;
import com.konkow.framework.domain.master.DropdownValue;
import com.konkow.framework.repository.impl.DropdownValueRepository;
import com.konkow.framework.repository.impl.SimpleDomainRepository;

@Controller
@RequestMapping(value = "/master")
public class MasterController {

    @Autowired
    private SimpleDomainRepository sdRepository;

    @Autowired
    private DropdownValueRepository dRepository;

    @RequestMapping(value = "/documentStatus/getAll", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody Result<AbstractSimpleDomain> getAllDocumentStatus() {
        Result<AbstractSimpleDomain> result = sdRepository.findAll(DocumentStatus.class);
        return result.getFullResult();
    }

    @RequestMapping(value = "/dropdownValue/getAll", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody Result<AbstractSimpleDomain> getAllDropdownValue() {
        Result<AbstractSimpleDomain> result = sdRepository.findAll(DropdownValue.class);
        return result.getFullResult();
    }

}
