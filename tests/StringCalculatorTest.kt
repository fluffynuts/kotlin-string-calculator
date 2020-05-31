import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class StringCalculatorTest{
    @Test
    fun `given empty string, should return zero`() {
        // Arrange
        val sut = create()
        val input = ""
        val expected = 0
        // Act
        val result = sut.add(input)
        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun `given 1, should return 1`() {
        // Arrange
        val sut = create()
        val input = "1"
        val expected = 1
        // Act
        val result = sut.add(input)
        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun `given two numbers separated by comma, should return sum`() {
        // Arrange
        val sut = create()
        val input = "1,2"
        val expected = 3
        // Act
        val result = sut.add(input)
        // Assert
        assertEquals(expected, result)
    }
    
    @Test
    fun `given three numbers separated by comma, should return sum`() {
        // Arrange
        val sut = create();
        val input = "1,2,3"
        val expected = 6
        // Act
        val result = sut.add(input)
        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun `given two numbers separated by newline, should return sum`() {
        // Arrange
        val sut = create();
        val input = "1\n2";
        val expected = 3;
        // Act
        val result = sut.add(input);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    fun `given three numbers, separated by comma or newline, should return sum`() {
        // Arrange
        val sut = create();
        val input = "3\n2,5"
        val expected = 10
        // Act
        val result = sut.add(input)
        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun `should ignore numbers greater than 1000 in sum`() {
        // Arrange
        val sut = create();
        val input = "1,1001,2,4"
        val expected = 7
        // Act
        val result = sut.add(input);
        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun `should not ignore 1000 in sum`() {
        // Arrange
        val sut = create()
        val input = "1,1000,2"
        val expected = 1003
        // Act
        val result = sut.add(input);
        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun `should throw if given negative numbers`() {
        // Arrange
        val sut = create()
        val input = "1,-3,4"
        // Act
        assertFailsWith<NegativesNotAllowed> {
            sut.add(input)
        }
        // Assert
    }

    @Test
    fun `should respect single-char custom delimiter`() {
        // Arrange
        val sut = create();
        val input = "//;\n1;2;3"
        val expected = 6
        // Act
        val result = sut.add(input)
        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun `should respect multi-char custom delimiter`() {
        // Arrange
        val sut = create()
        val input = "//**\n3**5**1"
        val expected = 9
        // Act
        val result = sut.add(input)
        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun `should respect multiple multi-char custom delimiters`() {
        // Arrange
        val sut = create()
        val input = "//[**][;][%%%]\n4%%%5**6;1"
        val expected = 16
        // Act
        val result = sut.add(input)
        // Assert
        assertEquals(expected, result)
    }

    private fun create(): StringCalculator {
        return StringCalculator();
    }
}