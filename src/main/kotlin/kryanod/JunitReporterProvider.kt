package kryanod

import com.pinterest.ktlint.core.Reporter
import com.pinterest.ktlint.core.ReporterProvider
import java.io.PrintStream

class JunitReporterProvider : ReporterProvider {
    override val id: String = "junit"
    override fun get(out: PrintStream, opt: Map<String, String>): Reporter =
        JunitReporter(out)
}
