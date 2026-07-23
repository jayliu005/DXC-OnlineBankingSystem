package com.dxc.dxconlinebanking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

class MigrationScriptsTests {

	@Test
	void migrationsCreateOnlyTheExpectedEmptySchema() throws IOException {
		assertCreateOnlyMigration("db/migration/V001__create_bank_user.sql", "BANK_USER");
		assertCreateOnlyMigration("db/migration/V002__create_account.sql", "ACCOUNT");
		assertCreateOnlyMigration("db/migration/V003__create_transaction_rec.sql", "TRANSACTION_REC");
	}

	private void assertCreateOnlyMigration(String path, String tableName) throws IOException {
		var migration = new ClassPathResource(path);
		assertTrue(migration.exists(), () -> "Missing migration: " + path);

		var sql = migration.getContentAsString(StandardCharsets.UTF_8).toUpperCase();
		assertTrue(sql.contains("CREATE TABLE " + tableName));
		assertFalse(sql.matches("(?s).*\\b(DROP|INSERT|UPDATE|DELETE|MERGE)\\b.*"));
	}

}
