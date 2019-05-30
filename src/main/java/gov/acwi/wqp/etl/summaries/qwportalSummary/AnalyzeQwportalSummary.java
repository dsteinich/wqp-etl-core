package gov.acwi.wqp.etl.summaries.qwportalSummary;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.AnalyzeTableDefault;
import gov.acwi.wqp.etl.EtlConstantUtils;

@Component
@StepScope
public class AnalyzeQwportalSummary extends AnalyzeTableDefault {

	@Autowired
	public AnalyzeQwportalSummary(JdbcTemplate jdbcTemplate,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getBaseTableName() {
		return QwportalSummary.BASE_TABLE_NAME;
	}
}
