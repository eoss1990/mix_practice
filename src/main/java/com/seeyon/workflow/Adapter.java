package com.seeyon.workflow;

import org.dom4j.Document;

import java.util.List;

/**
 * Created by yangyu on 16/10/30.
 */
public interface Adapter {
    Document execute(Document document);

    Document workflow(Document document);

    boolean rout(String condition);
}
