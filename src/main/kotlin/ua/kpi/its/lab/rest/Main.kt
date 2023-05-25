package ua.kpi.its.lab.rest

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet
import java.util.*
import org.springframework.web.filter.DelegatingFilterProxy
import org.eclipse.jetty.servlet.FilterHolder
import jakarta.servlet.DispatcherType

private val logger: Logger
    get() = LogManager.getLogger()

fun main() {
    startJetty()
}

private fun startJetty() {
    logger.info("Starting server at port {}", 8080)
    val server = Server(8080).apply {
        handler = servletContextHandler
        addRuntimeShutdownHook()
    }

    server.start()
    logger.info("Server started at port {}", 8080)
    server.join()
}

private val servletContextHandler: ServletContextHandler
    get() {
        val filter = DelegatingFilterProxy("springSecurityFilterChain", webAppContext)
        val webAppContext = webApplicationContext
        val dispatcherServlet = DispatcherServlet(webAppContext)
        val springServletHolder = ServletHolder("dispatcherServlet", dispatcherServlet)
        return ServletContextHandler(ServletContextHandler.SESSIONS).apply {
            addFilter(FilterHolder(filter), "/*", EnumSet.of(DispatcherType.REQUEST))
            errorHandler = null
            contextPath = "/"
            addServlet(springServletHolder, "/*")
            addEventListener(ContextLoaderListener(webAppContext))
        }
    }

private val webApplicationContext: WebApplicationContext
    get() = AnnotationConfigWebApplicationContext().apply {
        setConfigLocation("ua.kpi.its.lab.rest.config")
    }
private fun Server.addRuntimeShutdownHook() {
    Runtime.getRuntime().addShutdownHook(Thread {
        if (isStarted) {
            stopAtShutdown = true
            try {
                stop()
            } catch (e: Exception) {
                logger.error("Error while stopping jetty server: ${e.message}", e)
            }
        }
    })
}