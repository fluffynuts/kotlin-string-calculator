import java.io.Serializable
import java.lang.Exception

public class StringCalculator {
    fun add(input: String): Int {
        return parse(input)
            .split()
            .map { s -> s.toInt() }
            .filter { i -> i <= 1000 }
            .throwIfHaveNegatives()
            .sum()
    }

    private fun parse(input: String): Pair<String, Array<String>> {
        return if (input.startsWith("//")) {
            parseCustomDelimiters(input)
        } else {
            Pair(input.zeroIfEmpty(), arrayOf(",", "\n"))
        }
    }

    private fun parseCustomDelimiters(input: String): Pair<String, Array<String>> {
        return input.split("\n")
            .mapIndexed { idx, el ->
                if (idx == 0) {
                    el.substring(2)
                        .trim('[', ']')
                        .split("][")
                        .toTypedArray()
                } else {
                    el
                }
            }.asPair()
    }
}

private fun Iterable<Serializable>.asPair(): Pair<String, Array<String>> {
    return Pair(
        this.drop(1).first() as String,
        this.first() as Array<String>
    )
}

private fun Pair<String, Array<String>>.split(): Array<String> {
    return this.first.split(*this.second)
        .toTypedArray()
}

private fun String.zeroIfEmpty(): String {
    return if (this == "") { "0" } else { this }
}

private fun Iterable<Int>.throwIfHaveNegatives(): Iterable<Int> {
    val negatives = this.filter { i -> i < 0 }
    if (negatives.any()) {
        throw NegativesNotAllowed(*negatives.toIntArray())
    }
    return this
}

public class NegativesNotAllowed(vararg numbers: Int) :
    Exception("Negatives not allowed: ${numbers.joinToString(",")}") {
}

