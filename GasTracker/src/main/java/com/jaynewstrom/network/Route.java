package com.jaynewstrom.network;

/**
 * Created by jaynewstrom on 10/5/13.
 */
public enum Route {
    GET_FUEL_GRADES("grades", RequestMethod.GET, RetryPolicy.MAX),
    POST_GAS_PRICE("prices", RequestMethod.POST, RetryPolicy.NONE);

    final String route;
    final RequestMethod requestMethod;
    final RetryPolicy retryPolicy;

    private Route(String route, RequestMethod requestMethod, RetryPolicy retryPolicy) {
        this.route = route;
        this.requestMethod = requestMethod;
        this.retryPolicy = retryPolicy;
    }
}
