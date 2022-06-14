select * from smer;

-- SMER podaci
insert into smer (id , naziv, oznaka)
 values (nextval('smer_seq'), 'Inzenjerstvo informacionih sistema', 'IT'),
		(nextval('smer_seq'), 'Industrijsko inzenjerstvo', 'II' ),
		(nextval('smer_seq'), 'Inzenjererski menadzment', 'IM' ),
        (nextval('smer_seq'), 'Racunarstvo i automatika', 'RA' ),
        (nextval('smer_seq'), 'Graficki dizajn', 'GRID' );

insert into smer (id , naziv, oznaka)
 values (-100, 'TestInzenjerstvo informacionih sistema', 'TestIT');


-- PROJEKAT podaci
insert into projekat (id, naziv, oznaka, opis)
 values (nextval('projekat_seq'), 'Web aplikacija' , 'WEB', 'Razvoj web aplikacije'),
		(nextval('projekat_seq'), 'SolidWorks', 'SW', 'Kreiranje 3D modela' ),
		(nextval('projekat_seq'), 'Marketing', 'MKT', 'Marketing strategija' ),
        (nextval('projekat_seq'), 'Robotizovani sistemi', 'RS', 'Kreiranje robota' ),
        (nextval('projekat_seq'), 'Dizajn enterijera', 'DE', 'Osmisljavanje dizajna enterijera' );
        
        	insert into projekat (id, naziv, oznaka, opis)
 values (-100, 'TestWeb aplikacija' , 'TestWEB', 'TestRazvoj web aplikacije');
        
-- GRUPA podaci
insert into grupa (id, oznaka, smer)
 values (nextval('grupa_seq'), 'IT-A1', 1),
		(nextval('grupa_seq'), 'II-A2', 2 ),
		(nextval('grupa_seq'), 'IM-A3', 3 ),
        (nextval('grupa_seq'), 'RA-A4', 4 ),
        (nextval('grupa_seq'), 'GRID-A5', 5 );
        
        insert into grupa (id, oznaka, smer)
 values (-100, 'TestIT-A1', 1);
 
        
-- STUDENT podaci
insert into student (id, ime, prezime, broj_indeksa, grupa, projekat)
 values (nextval('student_seq'), 'Dimitrije' , 'Volarevic', 'IT51-2019', 1 ,1),
		(nextval('student_seq'), 'Dusan', 'Volarevic', 'II22-2019', 2 , 2),
		(nextval('student_seq'), 'Aleksa', 'Miljkovic', 'IM108-2019',3 , 3),
        (nextval('student_seq'), 'Luka', 'Dragicevic', 'RA50-2019',4 ,4 ),
        (nextval('student_seq'), 'Pavle', 'Stanisic', 'GRID20-2019',5 ,5);
        
        insert into student (id, ime, prezime, broj_indeksa, grupa, projekat)
 values (-100, 'TestDimitrije' , 'TestVolarevic', 'TestIT51-2019', 1 ,1);