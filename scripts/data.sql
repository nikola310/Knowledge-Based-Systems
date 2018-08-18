/* KORISNICI */
INSERT INTO USER VALUES(1, 'Nikola', 'S', 'nikola', 'A', '123');
INSERT INTO USER VALUES(2, 'Mika', 'm', 'mika', 'D', '123');

/* PACIJENTI */
INSERT INTO PATIENT VALUES(1, 'Kadir', 'T', 120, 37);

/* BOLESTI */
INSERT INTO DISEASE VALUES(1, NULL, 'Cold', 'PREHL');
INSERT INTO DISEASE VALUES(2, NULL, 'Fever', 'GROZN');
INSERT INTO DISEASE VALUES(3, NULL, 'Tonsillitis', 'UPAKR');
INSERT INTO DISEASE VALUES(4, NULL, 'Sinus infection', 'SININ');
INSERT INTO DISEASE VALUES(5, NULL, 'Hypertension', 'HIPTE');
INSERT INTO DISEASE VALUES(6, NULL, 'Diabetes', 'DIJBE');
INSERT INTO DISEASE VALUES(7, NULL, 'Chronic kidney disease', 'HRBUB');
INSERT INTO DISEASE VALUES(8, NULL, 'Acute kidney failure', 'AKBUB');

/* SIMPTOMI */
INSERT INTO SYMPTOM VALUES(1, 'Nose leaking', 'CURNO');
INSERT INTO SYMPTOM VALUES(2, 'Headache', 'GLAVB');
INSERT INTO SYMPTOM VALUES(3, 'Sneezing', 'KIJAN');
INSERT INTO SYMPTOM VALUES(4, 'Cough', 'KASHA');
INSERT INTO SYMPTOM VALUES(5, 'Sore throat', 'BOLUG');
INSERT INTO SYMPTOM VALUES(6, 'Temperature greater than 38°C', 'TEM38');
INSERT INTO SYMPTOM VALUES(7, 'Shivering', 'DRHTI');
INSERT INTO SYMPTOM VALUES(8, 'Pain spreading to the ears', 'BOLOU');
INSERT INTO SYMPTOM VALUES(9, 'Temperature in between 40°C and 41°C', 'T4041');
INSERT INTO SYMPTOM VALUES(10, 'Loss of appetite', 'GUBAP');
INSERT INTO SYMPTOM VALUES(11, 'Tiredness', 'TIRED');
INSERT INTO SYMPTOM VALUES(12, 'Yellow nasal secretion', 'ZHNOS');
INSERT INTO SYMPTOM VALUES(13, 'Swelling around the eyes', 'OOCHI');
INSERT INTO SYMPTOM VALUES(14, 'Frequent urination', 'CESTO');
INSERT INTO SYMPTOM VALUES(15, 'Loss of body weight', 'GUBTT');
INSERT INTO SYMPTOM VALUES(16, 'Fatigue', 'ZAMOR');
INSERT INTO SYMPTOM VALUES(17, 'Nausea and vomiting', 'MUPOV');
INSERT INTO SYMPTOM VALUES(18, 'Nocturia', 'NOCTA');
INSERT INTO SYMPTOM VALUES(19, 'Swelling around legs and joints', 'ONOZG');
INSERT INTO SYMPTOM VALUES(20, 'Strangulation', 'GUSHI');
INSERT INTO SYMPTOM VALUES(21, 'Chest pain', 'BOLGR');
INSERT INTO SYMPTOM VALUES(22, 'Diarrhea', 'PROLJ');
INSERT INTO SYMPTOM VALUES(23, 'In last 6 months high blood pressure has been registered at least 10 times', 'VISPR');
INSERT INTO SYMPTOM VALUES(24, 'In last 60 days patient had cold or fever', 'PRG60');
INSERT INTO SYMPTOM VALUES(25, 'Patient is recovering from operation', 'OPORO');
INSERT INTO SYMPTOM VALUES(26, 'Received antibiotics in last 21 days', 'ANB21');
INSERT INTO SYMPTOM VALUES(27, 'Disease with high body temperature has been observed in the last 14 days', 'TEM14');
INSERT INTO SYMPTOM VALUES(28, 'Patient is suffering from hipertension more than 6 months', 'HIPT6');
INSERT INTO SYMPTOM VALUES(29, 'Patient is suffering from diabetes', 'DIANW');

/* SIMPTOMI-BOLESTI */
INSERT INTO SYMPTOMDISEASE VALUES(1, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(2, 2, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(3, 3, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(4, 4, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(5, 5, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(6, 1, 2, 0);
INSERT INTO SYMPTOMDISEASE VALUES(7, 2, 2, 0);
INSERT INTO SYMPTOMDISEASE VALUES(8, 3, 2, 0);
INSERT INTO SYMPTOMDISEASE VALUES(9, 4, 2, 0);
INSERT INTO SYMPTOMDISEASE VALUES(10, 5, 2, 0);
INSERT INTO SYMPTOMDISEASE VALUES(11, 7, 2, 0);
INSERT INTO SYMPTOMDISEASE VALUES(12, 6, 2, 0);
INSERT INTO SYMPTOMDISEASE VALUES(13, 5, 3, 0);
INSERT INTO SYMPTOMDISEASE VALUES(14, 8, 3, 0);
INSERT INTO SYMPTOMDISEASE VALUES(15, 9, 3, 0);
INSERT INTO SYMPTOMDISEASE VALUES(16, 7, 3, 0);
INSERT INTO SYMPTOMDISEASE VALUES(17, 10, 3, 0);
INSERT INTO SYMPTOMDISEASE VALUES(18, 11, 3, 0);
INSERT INTO SYMPTOMDISEASE VALUES(19, 12, 3, 0);
INSERT INTO SYMPTOMDISEASE VALUES(20, 2, 3, 0);
INSERT INTO SYMPTOMDISEASE VALUES(21, 1, 4, 0);
INSERT INTO SYMPTOMDISEASE VALUES(22, 1, 4, 0);
INSERT INTO SYMPTOMDISEASE VALUES(23, 1, 4, 0);
INSERT INTO SYMPTOMDISEASE VALUES(24, 1, 4, 0);
INSERT INTO SYMPTOMDISEASE VALUES(25, 1, 4, 0);
INSERT INTO SYMPTOMDISEASE VALUES(26, 1, 4, 0);
INSERT INTO SYMPTOMDISEASE VALUES(27, 1, 4, 0);
INSERT INTO SYMPTOMDISEASE VALUES(28, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(29, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(30, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(31, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(32, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(33, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(34, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(35, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(36, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(37, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(38, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(39, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(40, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(41, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(42, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(43, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(44, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(45, 1, 1, 0);
INSERT INTO SYMPTOMDISEASE VALUES(46, 1, 1, 0);

/* DIJAGNOZE */
INSERT INTO DIAGNOSIS VALUES(1, 2, 1, 1, '2018-08-09', 1);

/* SASTOJCI */
INSERT INTO INGREDIENT VALUES(1, 'Abietis oil');
INSERT INTO INGREDIENT VALUES(2, 'Aripiprazole');
INSERT INTO INGREDIENT VALUES(3, 'Factor X');
INSERT INTO INGREDIENT VALUES(4, 'Fosfomycin trometamol');
INSERT INTO INGREDIENT VALUES(5, 'Abietis oil');

/* LEKOVI */
INSERT INTO MEDICINE VALUES(1, 'Paracetamol', 'P');
INSERT INTO MEDICINE VALUES(2, 'Ibuprofen', 'P');
INSERT INTO MEDICINE VALUES(3, 'Codeine', 'P');
INSERT INTO MEDICINE VALUES(4, 'Oritavancin', 'A');
INSERT INTO MEDICINE VALUES(5, 'Linezolid', 'A');
INSERT INTO MEDICINE VALUES(6, 'Tigecycline', 'A');
INSERT INTO MEDICINE VALUES(7, 'Zocor', 'O');
INSERT INTO MEDICINE VALUES(8, 'Clonazepam', 'O');
INSERT INTO MEDICINE VALUES(9, 'Lidex', 'O');