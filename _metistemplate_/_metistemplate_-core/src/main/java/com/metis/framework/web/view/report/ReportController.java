package com.metis.framework.web.view.report;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.konkow.framework.service.IAuthorizationService;
import com.konkow.framework.utils.ThaiBahtUtils;
import com.metis.framework.web.view.report.jasper.JasperReportWrapper;

@Controller
@RequestMapping("/report")
public class ReportController {

    private static final Logger LOGGER = LogManager.getLogger(ReportController.class);

    @Autowired
    private IAuthorizationService authorizationService;

    @RequestMapping(value = "/getSampleReport", method = RequestMethod.GET)
    @Transactional
    public void getSampleReport(HttpServletResponse response, @RequestParam Map<String, String> allRequestParams) {
        ReportParameters param = new ReportParameters("rep_fee_bill", "rep_fee_bill", allRequestParams, ReportType.PDF);
        Report report = new JasperReportWrapper(param, response);
        report.generate();
    }
    
    @RequestMapping(value = "/getFeeBillReport", method = RequestMethod.GET)
    @Transactional
    public void getFeeBillReport(HttpServletResponse response, @RequestParam Map<String, String> allRequestParams) {
        ThaiBahtUtils thaiBaht = new ThaiBahtUtils();
    	allRequestParams.put("number_in_text", thaiBaht.getText(allRequestParams.get("amount")));
    	
        ReportParameters param = new ReportParameters("rep_fee_bill", "rep_fee_bill", allRequestParams, ReportType.PDF);
        Report report = new JasperReportWrapper(param, response);
        report.generate();
    }

}
