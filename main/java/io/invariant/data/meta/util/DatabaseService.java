public class DatabaseService {
    /**
     * Safe query execution example
     */
    public void executeQuery(String userInput) {
        // First check the query
        QueryCheckResult checkResult = SQLSecurityChecker.checkQuery(userInput);
        
        if (checkResult.hasIssues()) {
            // Log the issues
            logger.warn("SQL security issues found:\n" + checkResult);
            
            if (!checkResult.getErrors().isEmpty()) {
                throw new SecurityException("SQL injection detected");
            }
        }

        // Use PreparedStatement for actual execution
        try (PreparedStatement stmt = connection.prepareStatement(userInput)) {
            // Set parameters using setString(), setInt(), etc.
            ResultSet rs = stmt.executeQuery();
            // Process results
        } catch (SQLException e) {
            logger.error("Database error", e);
            throw new RuntimeException("Database error", e);
        }
    }

    /**
     * Example of building a safe query
     */
    public String buildSafeQuery(String tableName, String columnName, String value) {
        // Validate table and column names
        if (!SQLSecurityChecker.isValidColumnName(tableName) || 
            !SQLSecurityChecker.isValidColumnName(columnName)) {
            throw new IllegalArgumentException("Invalid table or column name");
        }

        // Use PreparedStatement
        return "SELECT * FROM " + tableName + " WHERE " + columnName + " = ?";
    }
}
