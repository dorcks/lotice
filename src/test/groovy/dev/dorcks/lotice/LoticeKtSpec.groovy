package dev.dorcks.lotice

import spock.lang.Specification
import spock.lang.Unroll

class LoticeKtSpec extends Specification {
    @Unroll
    def "running main with invalid args: #args"() {
        when:
        LoticeKt.main(args as String[])

        then:
        thrown(IllegalArgumentException)

        where:
        args || _
        null  | _
        []    | _
    }

    @Unroll
    def "running main with args: #args"() {
        when:
        LoticeKt.main(args as String[])

        then:
        noExceptionThrown()

        where:
        args            || _
        ["arg1", "arg2"] | _
    }
}
