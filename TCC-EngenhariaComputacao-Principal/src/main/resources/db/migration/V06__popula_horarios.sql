ALTER TABLE Horario
CHANGE COLUMN horaMinuto hora_minuto TIME;

INSERT INTO `horario` VALUES (1,'07:00'),(2,'07:30'),(3,'08:00'),(4,'08:30'),(5,'09:00'),(6,'09:30'),(7,'10:00'),(8,'10:30'),(9,'11:00'),(10,'11:30'),(11,'13:00'),(12,'13:30'),(13,'14:00'),(14,'14:30'),(15,'15:00'),(16,'15:30'),(17,'16:00'),(18,'16:30'),(19,'17:00'),(20,'17:30');

ALTER TABLE agendamento_consulta MODIFY COLUMN data_consulta DATE;
ALTER TABLE agendamento_exame MODIFY COLUMN data_consulta DATE;