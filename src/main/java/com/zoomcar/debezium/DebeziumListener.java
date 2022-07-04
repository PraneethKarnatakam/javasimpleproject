package com.zoomcar.debezium;

import io.debezium.config.Configuration;
import io.debezium.embedded.EmbeddedEngine;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DebeziumListener
{
   public final EmbeddedEngine engine;

   private final Executor executor = Executors.newSingleThreadExecutor();

   public static DebeziumListener debeziumListener = new DebeziumListener(DebeziumConnector.getInstance().configuration);



   public DebeziumListener(Configuration configuration)
   {
      System.out.println("starting debezium listener");
      this.engine = EmbeddedEngine
              .create()
              .using(configuration)
              .notifying(this::handleEvent).build();
   }

   public void init()
   {
      System.out.println("inside init of debezium listener");
      this.executor.execute(engine);
   }
   @PostConstruct
   private void start() {
      System.out.println("executing the engine");
      //this.executor.execute(engine);
   }

   @PreDestroy
   private void stop() {
      System.out.println("trying to stop the engine");
      if (this.engine != null) {
         this.engine.stop();
      }
   }

   private void handleEvent(SourceRecord sourceRecord) {
      Struct sourceRecordValue = (Struct) sourceRecord.value();
      System.out.println("hey reached here");

   }


}
