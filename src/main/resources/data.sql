-- Insert some users
INSERT INTO app_user (full_name, email,  authority_type, user_visibility)
VALUES ('John Smith', 'john@example.com', 'ADMIN', 'HIDDEN');


INSERT INTO app_user (full_name, email,  authority_type, user_visibility)
VALUES ('Juli Zeh', 'juliZeh@gmail.com', 'USER', 'REGULAR');

INSERT INTO app_user (full_name, email,  authority_type, user_visibility)
VALUES ('Donna Tartt', 'donna.tartt@gmail.com', 'USER', 'REGULAR');

INSERT INTO app_user (full_name, email,  authority_type, user_visibility)
VALUES ('J.K. Rowling', 'jkrowling.com', 'USER', 'REGULAR');


INSERT INTO project (title, description, theme, project_status, project_type)
VALUES ('Project 1', 'This is the first project', 'Easter', 'FOUNDING', 'MUSIC');

INSERT INTO project (title, description, theme, project_status, project_type)
VALUES ('Project 2', 'This is the second project', 'Easter', 'FOUNDING', 'MUSIC');

INSERT INTO project (title, description, theme, project_status, project_type)
VALUES ('Project 3', 'This is the third project', 'Summer', 'FOUNDING', 'MOVIE');

INSERT INTO project (title, description, theme, project_status, project_type)
VALUES ('Project 4', 'This is the forth project', 'Holliday', 'FOUNDING', 'MOVIE');


INSERT INTO project_owner(project_id, user_id)
VALUES (1, 1);

INSERT INTO project_owner(project_id, user_id)
VALUES (2, 2);

INSERT INTO project_owner(project_id, user_id)
VALUES (3, 3);

INSERT INTO project_owner(project_id, user_id)
VALUES (4, 4);

INSERT INTO project_participant (project_id, user_id)
VALUES (1, 2),
       (1, 3);

INSERT INTO skill (name, description)
VALUES ('Rhythmic',
        'The basis of music and dance.');

INSERT INTO skill (name, description)
VALUES ('Vocal',
        'A communication skill that refers to the quality of a person''s voice and how they use it to convey their message.');

INSERT INTO skill (name, description)
VALUES ('Dancing',
        'Is the ability to move one''s body rhythmically to music or sound.');

INSERT INTO skill (name, description)
VALUES ('Java',
        'Java is a general-purpose computer-programming language that is concurrent, class-based, object-oriented, and specifically designed to have as few implementation dependencies as possible.');
INSERT INTO skill (name, description)
VALUES ('React',
        'Work with and write semantic HTML tags. Work with and write CSS selectors. Implement a CSS reset. Understand the box model and how to reset to border-box.');

INSERT INTO skill (name, description)
VALUES ('Python',
        'Python is an interpreted, high-level, general-purpose programming language. Created by Guido van Rossum and first released in 1991, Python has a design philosophy that emphasizes code readability, notably using significant whitespace.');


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
