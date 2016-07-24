package com.flow;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.flow.action.ReadFileActionTest;

@RunWith(Suite.class)
@SuiteClasses({
    ReadFileActionTest.class,
    CommonFlowTest.class,
})
public class AllTests {
}