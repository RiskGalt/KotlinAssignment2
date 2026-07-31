/**
 * Kotlin Assignment 2 - Code Challenges
 */

/**
 * Challenge A: Caesar Cipher Encryption
 * Shifts each letter of the string by the specified key integer.
 * Handles both uppercase and lowercase letters wrapping cleanly around the alphabet.
 * Non-alphabetic characters (spaces, punctuation) remain untouched.
 */
fun encryptString(input: String, key: Int): String {
    // Normalize the key to stay within 0-25 boundaries even if a large number is passed
    val shift = ((key % 26) + 26) % 26

    return input.map { char ->
        when {
            char.isLowerCase() -> {
                // Shift within 'a'..'z'
                ((char - 'a' + shift) % 26 + 'a'.code).toChar()
            }
            char.isUpperCase() -> {
                // Shift within 'A'..'Z'
                ((char - 'A' + shift) % 26 + 'A'.code).toChar()
            }
            else -> char // Leave spaces/punctuation exactly as they are
        }
    }.joinToString("")
}

/**
 * Challenge B: Anagram Checker
 * Compares two single words to determine if they are anagrams.
 * Converts to lowercase and compares their sorted character arrays.
 */
fun isAnagram(word1: String, word2: String): Boolean {
    val cleanWord1 = word1.lowercase().replace(" ", "")
    val cleanWord2 = word2.lowercase().replace(" ", "")

    if (cleanWord1.length != cleanWord2.length) return false

    return cleanWord1.toCharArray().sorted() == cleanWord2.toCharArray().sorted()
}

/**
 * Challenge C: Manual Substring Checker
 * Determines if [substring] is inside [mainString] without using String.contains().
 * Iterates through possible starting windows and matches character by character.
 */
fun manualContains(mainString: String, substring: String): Boolean {
    if (substring.isEmpty()) return true
    if (substring.length > mainString.length) return false

    // Slide a window over the main string up to the last possible starting point
    for (i in 0..mainString.length - substring.length) {
        var match = true

        // Check characters inside the current window
        for (j in substring.indices) {
            if (mainString[i + j] != substring[j]) {
                match = false
                break
            }
        }
        if (match) return true
    }
    return false
}

/**
 * Challenge D: Find the Longest Word
 * Analyzes a sentence and extracts the longest word.
 * Strips punctuation characters out so punctuation lengths don't alter the result.
 */
fun findLongestWord(input: String): String {
    // Split the text by spaces, then clean each word of surrounding punctuation marks
    val words = input.split(Regex("\\s+"))
    var longest = ""

    for (rawWord in words) {
        // Strip out periods, commas, exclamation points, etc., to isolate the word letters
        val cleanWord = rawWord.replace(Regex("[^a-zA-Z0-9-]"), "")
        if (cleanWord.length > longest.length) {
            longest = cleanWord
        }
    }
    return longest
}

fun main() {
    // Verification Driver Code

    println("--- Challenge A: Encryption ---")
    val secret = "Hello, World! xyz"
    println("Original: $secret")
    println("Encrypted (Key = 1): ${encryptString(secret, 1)}") // Expected: Ifmmp, Xpsme! yza

    println("\n--- Challenge B: Anagram Checker ---")
    val w1 = "dusty"
    val w2 = "study"
    println("Are '$w1' and '$w2' anagrams? ${isAnagram(w1, w2)}") // Expected: true

    println("\n--- Challenge C: Manual Substring ---")
    val fullText = "Kotlin programming language"
    val search = "program"
    println("Does '$fullText' contain '$search'? ${manualContains(fullText, search)}") // Expected: true

    println("\n--- Challenge D: Longest Word ---")
    val sentence = "Learning mobile application development using Kotlin is amazing!"
    println("Longest word: \"${findLongestWord(sentence)}\"") // Expected: application
}