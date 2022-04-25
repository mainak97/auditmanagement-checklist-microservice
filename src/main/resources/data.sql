insert into audit_type values(1,'Internal');

insert into audit_checklist_question(id,question,audit_type_id) values
	(1,'Have all Change requests followed SDLC before PROD move?',1),
	(2,'Have all Change requests been approved by the application owner?',1),
	(3,'Are all artifacts like CR document, Unit test cases available?',1),
	(4,'Is the SIT and UAT sign-off available?',1),
	(5,'Is data deletion from the system done with application owner approval?',1);