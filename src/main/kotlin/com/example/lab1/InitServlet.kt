import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@WebServlet(name = "InitServlet", value = ["/initservlet"])
class InitServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        servletContext.log("Client IP: ${req.remoteAddr}")
        servletContext.log("Params: ${req.parameterMap}")
        servletContext.log("Request method: ${req.method}")
        servletContext.log("User-agent: ${req.getHeader("User-Agent")}")

        val name = req.getParameter("name")
        val str = req.getParameter("str").toInt()
        val checkbox = req.getParameter("checkbox").toBoolean()

        if (checkbox) {
            res.contentType = "text/html"
            res.writer.println("<html><body>")
            res.writer.println("<h1>InitServlet</h1>")
            res.writer.println("<div>Checked</div>")
            res.writer.println("</body></html>")
        } else {
            val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
            val line = "${name.repeat(str)}, $timestamp\n"
            val file = File("template.html")
            FileWriter(file, true).use { it.write(line) }
            res.contentType = "text/html"
            res.writer.println("<html><body>")
            res.writer.println("<h1>InitServlet</h1>")
            res.writer.println("<div>")
            res.writer.println(file.readText())
            res.writer.println("</div>")
            res.writer.println("</body></html>")
        }
    }
}