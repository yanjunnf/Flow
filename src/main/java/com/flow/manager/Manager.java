package com.flow.manager;

import java.util.List;

import com.flow.common.Status;
import com.flow.recipe.Flow;

public interface Manager {
    public boolean start(String name);
    public boolean delete(String name);
    public boolean stop(String name);
    public boolean restart(String name);
    public List<Flow> query(Status status);
    public Flow get(String name);
    public Flow copy(Flow flow);
}
