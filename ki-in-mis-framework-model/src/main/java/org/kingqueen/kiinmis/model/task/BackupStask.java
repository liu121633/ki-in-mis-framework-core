package org.kingqueen.kiinmis.model.task;

import java.io.IOException;

import org.kingqueen.kiinmis.model.biz.DatabaseManagement.DataBackupRecovery;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 自动备份数据库的task
 * @author liu12
 *
 */
public class BackupStask extends QuartzJobBean {
	
	private DataBackupRecovery dataBackupRecovery;
	
	public DataBackupRecovery getDataBackupRecovery() {
		return dataBackupRecovery;
	}

	public void setDataBackupRecovery(DataBackupRecovery dataBackupRecovery) {
		this.dataBackupRecovery = dataBackupRecovery;
	}


	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {
			dataBackupRecovery.dataBackup();
			System.out.println("备份了一次");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
