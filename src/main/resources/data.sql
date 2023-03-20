-- Insert some users
INSERT INTO app_user (full_name, email, password, authority_type, user_visibility)
VALUES ('John Smith', 'john@example.com', 'zoro123', 'ADMIN', 'HIDDEN');


INSERT INTO app_user (full_name, email, password, authority_type, user_visibility)
VALUES ('Juli Zeh', 'juliZeh@gmail.com', 'zoeeeh', 'USER', 'REGULAR');

INSERT INTO app_user (full_name, email, password, authority_type, user_visibility)
VALUES('Donna Tartt', 'donna.tartt@gmail.com', 'donnis', 'USER', 'REGULAR');

INSERT INTO app_user (full_name, email, password, authority_type, user_visibility)
VALUES('J.K. Rowling', 'jkrowling.com', 'jkrowling', 'USER', 'REGULAR');


INSERT INTO project (title, description, theme, project_status, project_type)
VALUES ('Project 1', 'This is the first project','Easter', 'FOUNDING', 'MUSIC');

INSERT INTO project (title, description, theme, project_status, project_type)
VALUES ('Project 2', 'This is the second project','Easter', 'FOUNDING', 'MUSIC');


INSERT INTO project_owner(project_id,user_id)
VALUES (1,1);

INSERT INTO project_participant (project_id, user_id)
VALUES (1, 2), (1, 3);

INSERT INTO skill (name,description)
VALUES ('Java', 'Java is a general-purpose computer-programming language that is concurrent, class-based, object-oriented, and specifically designed to have as few implementation dependencies as possible.');

INSERT INTO skill (name,description)
VALUES ('Python', 'Python is an interpreted, high-level, general-purpose programming language. Created by Guido van Rossum and first released in 1991, Python has a design philosophy that emphasizes code readability, notably using significant whitespace.');



-- Associate skills with users and projects
INSERT INTO user_skill (user_id, skill_id)
VALUES (1, 1);

INSERT INTO user_skill (user_id, skill_id)
VALUES (2, 2);

INSERT INTO project_skill (project_id, skill_id)
VALUES (1, 1);

INSERT INTO project_skill (project_id, skill_id)
VALUES (1, 2);

INSERT INTO project_skill (project_id, skill_id)
VALUES (2, 2);
