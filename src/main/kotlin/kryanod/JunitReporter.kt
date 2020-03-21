package kryanod

import com.pinterest.ktlint.core.LintError
import com.pinterest.ktlint.core.Reporter
import java.io.PrintStream
import java.util.concurrent.ConcurrentHashMap

class JunitReporter(val out: PrintStream) : Reporter {

    private val acc = ConcurrentHashMap<String, MutableList<LintError>>()

    override fun onLintError(file: String, err: LintError, corrected: Boolean) {
        if (!corrected) {
            acc.getOrPut(file) { ArrayList() }.add(err)
        }
    }

    override fun afterAll() {
        out.println("""<?xml version="1.0" encoding="UTF-8"?>""")
        out.println("<testsuite>")

        for (entry in acc.entries.sortedBy { it.key }) {
            val (file, errList) = entry
            for (err in errList) {
                val (line, col, ruleId, detail) = err

                val name = (file.split("/")
                    .last()
                    .split(".")
                    .takeIf { it.size == 2 }
                    ?.firstOrNull()
                    ?.takeIf { it.isNotBlank() }
                    ?.let { "$it:$line - $detail" }
                    ?: detail)
                    .escapeXmlChars()

                val className = file.split(Regex("src/\\w+/(java|kotlin)/"))
                    .takeIf { it.size == 2 }
                    ?.lastOrNull()
                    ?.replace('/', '.')
                    ?.replace(".kt", "")
                    ?: file

                val systemOutput = "Problem: ${detail.escapeXmlChars()}\n" +
                        "Rule id: $ruleId\n" +
                        "File: $file\n" +
                        "Line: $line\n" +
                        "Column: $col"

                out.println("""<testcase name="$name" classname="$className">""")
                out.println("<failure>$systemOutput</failure>")
                out.println("</testcase>")
            }
        }

        out.println("""</testsuite>""")
    }

    private fun String.escapeXmlChars() =
        this.replace("&", "&amp;")
            .replace("\"", "&quot;")
            .replace("'", "&apos;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
}
