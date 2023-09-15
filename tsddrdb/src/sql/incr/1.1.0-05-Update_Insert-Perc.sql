UPDATE tsddr_t_linea_sotto_linea_perc set data_inizio_validita = '2019-01-01';

UPDATE tsddr_t_linea_sotto_linea_perc set data_fine_validita = '2021-12-31' where id_linea_sotto_linea_perc = 1;
UPDATE tsddr_t_linea_sotto_linea_perc set data_fine_validita = '2021-12-31' where id_linea_sotto_linea_perc = 2;
UPDATE tsddr_t_linea_sotto_linea_perc set data_fine_validita = '2021-12-31' where id_linea_sotto_linea_perc = 13;
UPDATE tsddr_t_linea_sotto_linea_perc set data_fine_validita = '2021-12-31' where id_linea_sotto_linea_perc = 14;

INSERT INTO tsddr_t_linea_sotto_linea_perc(id_linea_sotto_linea_perc, id_linea, id_sotto_linea, per_min_recupero, per_max_scarto, data_inizio_validita) VALUES
(19, 1, null, 85, 0, '2022-01-01'),
(20, 1, 2, 85, 0, '2022-01-01'),
(21, 2, 13, 50, 0, '2022-01-01'),
(22, 3, 14, 50, 0, '2022-01-01'),
(23, 1, null, 99, 0, '2019-01-01'),
(24, 2, null, 99, 0, '2019-01-01'),
(25, 3, null, 99, 0, '2019-01-01');
