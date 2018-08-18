/*==============================================================*/
/* Table: ALLERGY                                               */
/*==============================================================*/
create table ALLERGY
(
   ALLERGY_ID           bigint not null auto_increment  comment '',
   PATIENT_ID           bigint not null  comment '',
   INGREDIENT_ID        bigint not null  comment '',
   primary key (ALLERGY_ID)
);

/*==============================================================*/
/* Table: DIAGNOSIS                                             */
/*==============================================================*/
create table DIAGNOSIS
(
   DIAGNOSIS_ID         bigint not null auto_increment  comment '',
   USER_ID              bigint not null  comment '',
   PATIENT_ID           bigint not null  comment '',
   DISEASE_ID           bigint not null  comment '',
   DIAGNOSIS_DATE       datetime not null  comment '',
   DIAGNOSIS_ACTIVE     bool not null  comment '',
   primary key (DIAGNOSIS_ID)
);

/*==============================================================*/
/* Table: DISEASE                                               */
/*==============================================================*/
create table DISEASE
(
   DISEASE_ID           bigint not null auto_increment  comment '',
   MEDICINE_ID          bigint  comment '',
   DISEASE_NAME         varchar(100) not null  comment '',
   DISEASE_CODE         char(5) not null  comment '',
   primary key (DISEASE_ID),
   key AK_IDENTIFIER_2 (DISEASE_CODE)
);

/*==============================================================*/
/* Table: INGREDIENT                                            */
/*==============================================================*/
create table INGREDIENT
(
   INGREDIENT_ID        bigint not null auto_increment  comment '',
   INGREDIENT_NAME      varchar(250) not null  comment '',
   primary key (INGREDIENT_ID)
);

/*==============================================================*/
/* Table: INGREDIENTMEDICINE                                    */
/*==============================================================*/
create table INGREDIENTMEDICINE
(
   IM_ID                bigint not null auto_increment  comment '',
   MEDICINE_ID          bigint not null  comment '',
   INGREDIENT_ID        bigint not null  comment '',
   primary key (IM_ID)
);

/*==============================================================*/
/* Table: MEDICINE                                              */
/*==============================================================*/
create table MEDICINE
(
   MEDICINE_ID          bigint not null auto_increment  comment '',
   MEDICINE_NAME        varchar(150) not null  comment '',
   MEDICINE_TYPE        char(1) not null  comment '',
   primary key (MEDICINE_ID)
);

/*==============================================================*/
/* Table: MEDICINEALLERGY                                       */
/*==============================================================*/
create table MEDICINEALLERGY
(
   MED_ID               bigint not null auto_increment  comment '',
   PATIENT_ID           bigint not null  comment '',
   MEDICINE_ID          bigint not null  comment '',
   primary key (MED_ID)
);

/*==============================================================*/
/* Table: PATIENT                                               */
/*==============================================================*/
create table PATIENT
(
   PATIENT_ID           bigint not null auto_increment  comment '',
   PATIENT_NAME         varchar(100) not null  comment '',
   PATIENT_SURNAME      varchar(250) not null  comment '',
   PATIENT_BLOOD_PRESSURE int  comment '',
   PATIENT_TEMPERATURE  float  comment '',
   primary key (PATIENT_ID)
);

/*==============================================================*/
/* Table: SYMPTOM                                               */
/*==============================================================*/
create table SYMPTOM
(
   SYM_ID               bigint not null auto_increment  comment '',
   SYM_DESC             varchar(300) not null  comment '',
   SYM_CODE             char(5) not null  comment '',
   primary key (SYM_ID),
   key AK_IDENTIFIER_2 (SYM_CODE)
);

/*==============================================================*/
/* Table: SYMPTOMDISEASE                                        */
/*==============================================================*/
create table SYMPTOMDISEASE
(
   SD_ID                bigint not null auto_increment  comment '',
   SYM_ID               bigint not null  comment '',
   DISEASE_ID           bigint not null  comment '',
   SD_SPECIFIC          bool not null  comment '',
   primary key (SD_ID)
);

/*==============================================================*/
/* Table: THERAPY                                               */
/*==============================================================*/
create table THERAPY
(
   THERAPY_ID           bigint not null auto_increment  comment '',
   MEDICINE_ID          bigint not null  comment '',
   PATIENT_ID           bigint not null  comment '',
   USER_ID              bigint not null  comment '',
   THERAPY_DATE         datetime not null  comment '',
   primary key (THERAPY_ID)
);

/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
create table USER
(
   USER_ID              bigint not null auto_increment  comment '',
   USER_NAME            varchar(100) not null  comment '',
   USER_SURNAME         varchar(200) not null  comment '',
   USER_USERNAME        varchar(150) not null  comment '',
   USER_TYPE            char(1) not null  comment '',
   USER_PASSWORD        varchar(250) not null  comment '',
   primary key (USER_ID),
   key AK_IDENTIFIER_2 (USER_USERNAME)
);

alter table ALLERGY add constraint FK_ALLERGY_RELATIONS_PATIENT foreign key (PATIENT_ID)
      references PATIENT (PATIENT_ID) on delete restrict on update restrict;

alter table ALLERGY add constraint FK_ALLERGY_RELATIONS_INGREDIE foreign key (INGREDIENT_ID)
      references INGREDIENT (INGREDIENT_ID) on delete restrict on update restrict;

alter table DIAGNOSIS add constraint FK_DIAGNOSI_RELATIONS_DISEASE foreign key (DISEASE_ID)
      references DISEASE (DISEASE_ID) on delete restrict on update restrict;

alter table DIAGNOSIS add constraint FK_DIAGNOSI_RELATIONS_USER foreign key (USER_ID)
      references USER (USER_ID) on delete restrict on update restrict;

alter table DIAGNOSIS add constraint FK_DIAGNOSI_RELATIONS_PATIENT foreign key (PATIENT_ID)
      references PATIENT (PATIENT_ID) on delete restrict on update restrict;

alter table DISEASE add constraint FK_DISEASE_IS_CURED__MEDICINE foreign key (MEDICINE_ID)
      references MEDICINE (MEDICINE_ID) on delete restrict on update restrict;

alter table INGREDIENTMEDICINE add constraint FK_INGREDIE_RELATIONS_INGREDIE foreign key (INGREDIENT_ID)
      references INGREDIENT (INGREDIENT_ID) on delete restrict on update restrict;

alter table INGREDIENTMEDICINE add constraint FK_INGREDIE_RELATIONS_MEDICINE foreign key (MEDICINE_ID)
      references MEDICINE (MEDICINE_ID) on delete restrict on update restrict;

alter table MEDICINEALLERGY add constraint FK_MEDICINE_RELATIONS_MEDICINE foreign key (MEDICINE_ID)
      references MEDICINE (MEDICINE_ID) on delete restrict on update restrict;

alter table MEDICINEALLERGY add constraint FK_MEDICINE_RELATIONS_PATIENT foreign key (PATIENT_ID)
      references PATIENT (PATIENT_ID) on delete restrict on update restrict;

alter table SYMPTOMDISEASE add constraint FK_SYMPTOMD_RELATIONS_SYMPTOM foreign key (SYM_ID)
      references SYMPTOM (SYM_ID) on delete restrict on update restrict;

alter table SYMPTOMDISEASE add constraint FK_SYMPTOMD_RELATIONS_DISEASE foreign key (DISEASE_ID)
      references DISEASE (DISEASE_ID) on delete restrict on update restrict;

alter table THERAPY add constraint FK_THERAPY_RELATIONS_PATIENT foreign key (PATIENT_ID)
      references PATIENT (PATIENT_ID) on delete restrict on update restrict;

alter table THERAPY add constraint FK_THERAPY_RELATIONS_MEDICINE foreign key (MEDICINE_ID)
      references MEDICINE (MEDICINE_ID) on delete restrict on update restrict;

alter table THERAPY add constraint FK_THERAPY_RELATIONS_USER foreign key (USER_ID)
      references USER (USER_ID) on delete restrict on update restrict;
