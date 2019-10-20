package com.sondo65.basemvp.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by hrskrs on 5/8/2017.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
