package gov.acwi.wqp.etl.codes.characteristicName.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;

public class SetupCharacteristicNameSwapTableFlowIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = "select table_catalog, table_schema, table_name, table_type from information_schema.tables where table_name='char_name_swap_stewards'";
	public static final String EXPECTED_DATABASE_TABLE = "tables";

	@Autowired
	@Qualifier("setupCharacteristicNameSwapTableFlow")
	private Flow setupCharacteristicNameSwapTableFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("setupCharacteristicNameSwapTableFlowTest")
				.start(setupCharacteristicNameSwapTableFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/characteristicName/drop.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void dropCharacteristicNameSwapTableStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("dropCharacteristicNameSwapTableStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/characteristicName/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void createCharacteristicNameSwapTableStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("createCharacteristicNameSwapTableStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/characteristicName/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void setupCharacteristicNameSwapTableFlowTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}