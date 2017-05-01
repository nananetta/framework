package com.metis._metistemplate_.web.view;

import com.konkow.framework.FrameworkProperties;

public final class ProjectProperties extends FrameworkProperties {

    public static String getDefaultGreeting() {
        return p.getProperty("default.greeting");
    }

}
