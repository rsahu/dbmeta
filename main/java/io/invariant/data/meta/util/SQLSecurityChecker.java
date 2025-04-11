import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class SQLSecurityChecker {
    // Set of common SQL reserved keywords
    private static final Set<String> RESERVED_KEYWORDS = new HashSet<String>() {{
        add("SELECT"); add("INSERT"); add("UPDATE"); add("DELETE"); add("DROP");
        add("CREATE"); add("ALTER"); add("TABLE"); add("DATABASE"); add("SCHEMA");
        add("WHERE"); add("FROM"); add("JOIN"); add("UNION"); add("GROUP");
        add("ORDER"); add("HAVING"); add("LIMIT"); add("OFFSET"); add("BY");
        add("INTO"); add("VALUES"); add("SET"); add("EXEC"); add("EXECUTE");
        add("TRUNCATE"); add("PROCEDURE"); add("FUNCTION"); add("TRIGGER");
        add("GRANT"); add("REVOKE"); add("INDEX"); add("CONSTRAINT");
        // Add more keywords as needed
    }};

    // SQL Injection patterns
    private static final Pattern[] INJECTION_PATTERNS = {
        Pattern.compile("'.*'\\s*(?i)or\\s+'.*'\\s*=\\s*'.*'"),    // OR condition
        Pattern.compile("'.*'\\s*(?i)and\\s+'.*'\\s*=\\s*'.*'"),   // AND condition
        Pattern.compile("(?i)union\\s+(?:all\\s+)?select"),         // UNION SELECT
        Pattern.compile("(?i)insert\\s+into"),                      // INSERT INTO
        Pattern.compile("(?i)drop\\s+table"),                       // DROP TABLE
        Pattern.compile("(?i)exec(ute)?\\s+"),                      // EXEC/EXECUTE
        Pattern.compile("(?i)xp_.*"),                               // Extended stored procedures
        Pattern.compile("(?i)sp_.*"),                               // Stored procedures
        Pattern.compile(";.*"),                                     // Multiple statements
        Pattern.compile("--.*"),                                    // Single line comments
        Pattern.compile("/\\*.*\\*/"),                             // Multi-line comments
        Pattern.compile("@@.*")                                     // System variables
    };

    /**
     * Check if a query contains potential SQL injection
     */
    public static QueryCheckResult checkQuery(String query) {
        QueryCheckResult result = new QueryCheckResult();
        
        if (query == null || query.trim().isEmpty()) {
            result.addError("Query is null or empty");
            return result;
        }

        // Check for SQL injection patterns
        for (Pattern pattern : INJECTION_PATTERNS) {
            if (pattern.matcher(query).find()) {
                result.addError("Potential SQL injection detected: " + pattern.pattern());
            }
        }

        // Check for dangerous characters
        checkDangerousCharacters(query, result);

        // Check for reserved keywords
        checkReservedKeywords(query, result);

        return result;
    }

    /**
     * Check for dangerous characters
     */
    private static void checkDangerousCharacters(String query, QueryCheckResult result) {
        String dangerousChars = "';\"=&|<>-+/*\\";
        for (char c : dangerousChars.toCharArray()) {
            if (query.indexOf(c) != -1) {
                result.addWarning("Potentially dangerous character found: " + c);
            }
        }
    }

    /**
     * Check for reserved keywords
     */
    private static void checkReservedKeywords(String query, QueryCheckResult result) {
        String[] words = query.toUpperCase().split("\\s+");
        for (String word : words) {
            if (RESERVED_KEYWORDS.contains(word)) {
                result.addWarning("SQL reserved keyword found: " + word);
            }
        }
    }

    /**
     * Result class to hold check results
     */
    public static class QueryCheckResult {
        private final List<String> errors = new ArrayList<>();
        private final List<String> warnings = new ArrayList<>();

        public void addError(String error) {
            errors.add(error);
        }

        public void addWarning(String warning) {
            warnings.add(warning);
        }

        public boolean hasIssues() {
            return !errors.isEmpty() || !warnings.isEmpty();
        }

        public List<String> getErrors() {
            return new ArrayList<>(errors);
        }

        public List<String> getWarnings() {
            return new ArrayList<>(warnings);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (!errors.isEmpty()) {
                sb.append("Errors:\n");
                errors.forEach(error -> sb.append("- ").append(error).append("\n"));
            }
            if (!warnings.isEmpty()) {
                sb.append("Warnings:\n");
                warnings.forEach(warning -> sb.append("- ").append(warning).append("\n"));
            }
            return sb.toString();
        }
    }

    /**
     * Example usage and testing
     */
    public static void main(String[] args) {
        // Test cases
        String[] queries = {
            "SELECT * FROM users WHERE name = 'John'",
            "SELECT * FROM users WHERE id = 1 OR '1'='1'",
            "DROP TABLE users",
            "SELECT * FROM users; DELETE FROM users",
            "SELECT * FROM users WHERE name = 'John' -- ignore password check",
            "EXEC xp_cmdshell 'dir'",
            "SELECT @@version",
            "SELECT * FROM users WHERE name = 'John' /* comment */",
            "SELECT * FROM users UNION SELECT password FROM admin",
            "INSERT INTO users VALUES ('admin', 'password')"
        };

        // Test each query
        for (String query : queries) {
            System.out.println("\nChecking query: " + query);
            QueryCheckResult result = checkQuery(query);
            System.out.println(result);
        }
    }

    /**
     * Utility method to sanitize input
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        
        // Remove dangerous characters
        return input.replaceAll("[';\"=&|<>\\-+/*\\\\]", "");
    }

    /**
     * Check if a string is a valid column name
     */
    public static boolean isValidColumnName(String columnName) {
        if (columnName == null || columnName.trim().isEmpty()) {
            return false;
        }
        
        // Check if it's a reserved keyword
        if (RESERVED_KEYWORDS.contains(columnName.toUpperCase())) {
            return false;
        }
        
        // Check if it follows valid column name pattern
        return columnName.matches("^[a-zA-Z_][a-zA-Z0-9_]*$");
    }
}