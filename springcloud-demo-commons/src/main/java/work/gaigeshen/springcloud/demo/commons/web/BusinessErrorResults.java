package work.gaigeshen.springcloud.demo.commons.web;

import work.gaigeshen.springcloud.demo.commons.exception.BusinessErrorException;

import static work.gaigeshen.springcloud.demo.commons.web.BusinessErrorResultCode.BUSINESS_ERROR;

/**
 *
 * @author gaigeshen
 */
public abstract class BusinessErrorResults {

    private BusinessErrorResults() { }

    public static Result<?> createResult(BusinessErrorException ex) {
        return Results.create(DefaultResultCode.create(BUSINESS_ERROR, ex.getMessage()), ex.getData());
    }
}
