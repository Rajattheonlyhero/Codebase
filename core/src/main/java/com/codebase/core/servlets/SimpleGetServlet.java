package com.codebase.core.servlets;

import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.Replicator;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.paths=/bin/simple-get",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET
        })
public class SimpleGetServlet extends SlingAllMethodsServlet {

    @Reference
    private ResourceResolverFactory factory;

    @Reference
    private Replicator replicator;

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws IOException {
        final Map<String, Object> authInfo = Collections.singletonMap(
                ResourceResolverFactory.SUBSERVICE,
                "codebaseSystemUser1");
        try (ResourceResolver resolver = factory.getServiceResourceResolver(authInfo)) {
            Session session = resolver.adaptTo(Session.class);
            Node contentNode = session.getNode("/content/codebase/jcr:content");
            if (Objects.nonNull(contentNode)) {
                contentNode.setProperty("test", "works");
                session.save();
                replicator.replicate(session, ReplicationActionType.ACTIVATE, contentNode.getPath());
                resp.setStatus(200);
                resp.getWriter().write("Test is saved and replicated.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            resp.getWriter().write("Failed to save.");
        }
    }
}
