CREATE TABLE NOTIFICACION (
                                     id INTEGER PRIMARY KEY AUTOINCREMENT,
                                     fecha_notificacion DATETIME,
                                     texto TEXT
);


CREATE TABLE NOTIFICACION_PROMOCION (
                                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                                        codigo_promocion TEXT,
                                        fecha_expiracion DATE,
                                        FOREIGN KEY (id) REFERENCES NOTIFICACION(id)
);

CREATE TABLE NOTIFICACION_RADIO (
                                             id INTEGER PRIMARY KEY AUTOINCREMENT,
                                             latitud REAL,
                                             longitud REAL,
                                             id_vehiculo INTEGER,
                                             FOREIGN KEY (id) REFERENCES NOTIFICACION(id)
);

CREATE TABLE NOTIFICACION_ZONA (
                                             id INTEGER PRIMARY KEY AUTOINCREMENT,
                                             latitud REAL,
                                             longitud REAL,
                                             nivel_peligro TEXT,
                                             id_vehiculo INTEGER,
                                             FOREIGN KEY (id) REFERENCES NOTIFICACION(id)
);