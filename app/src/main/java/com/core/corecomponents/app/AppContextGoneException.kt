package com.core.corecomponents.app

class AppContextGoneException: Exception("App context is null, try calling init function of the " +
        "implementing class")