package net.phenotype.SpringBatch.Listener.ListenerRepo;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class JobListener implements JobExecutionListener {

    private JavaMailSender javaMailSender;

    public JobListener(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();

        SimpleMailMessage mail = getSimpleMailMessage(String.format("%s is starting",jobName),
                String.format("Please note that the job, %s, is starting now. This is an auto generated email and does not require any response from you.",jobName));

        javaMailSender.send(mail);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        String jobName = jobExecution.getJobInstance().getJobName();

        SimpleMailMessage mail = getSimpleMailMessage(String.format("%s is completed",jobName),
                String.format("Please note that the job, %s, is completed now. This is an auto generated email and does not require any response from you.",jobName));

        javaMailSender.send(mail);

    }

    private SimpleMailMessage getSimpleMailMessage(String subject, String text){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo("toEmailID@gmail.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        simpleMailMessage.setCc("ccEmailID@gmail.com");
        simpleMailMessage.setFrom("fromEmailId@outlook.com");

        return simpleMailMessage;
    }
}
