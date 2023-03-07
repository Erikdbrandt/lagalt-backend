-- Insert some users
INSERT INTO app_user (full_name, email, password, authority_type, user_visibility)
VALUES ('John Smith', 'john@example.com', 'zoro123', 'null', 'PUBLIC');

INSERT INTO app_user (full_name, email, password, authority_type, user_visibility)
VALUES ('Jane Doe', 'jane@example.com', 'zoro123', 'null', 'PRIVATE');

-- Insert some skills
-- INSERT INTO skill (name)
-- VALUES ('Java');
--
-- INSERT INTO skill (name)
-- VALUES ('Python');

-- Insert some projects
INSERT INTO project (title, description, theme, project_status, project_type)
VALUES ('Project 1', 'This is the first project','Easter', 'FOUNDING', 'MUSIC');

INSERT INTO project (title, description, theme, project_status, project_type)
VALUES ('Project 2', 'This is the second project','Holiday', 'FOUNDING', 'FILM');

-- Associate users with projects
INSERT INTO project_participant (project_id, user_id)
VALUES (1, 2);

INSERT INTO project_participant (project_id, user_id)
VALUES (2, 1);

INSERT INTO project_owner(project_id,user_id)
VALUES (1,1);
INSERT INTO project_owner(project_id,user_id)
VALUES (2,2);
--
-- -- Associate skills with users and projects
-- INSERT INTO app_user_skill (user_id, skill_id)
-- VALUES (1, 1);
--
-- INSERT INTO app_user_skill (user_id, skill_id)
-- VALUES (2, 2);
--
-- INSERT INTO project_skill (project_id, skill_id)
-- VALUES (1, 1);
--
-- INSERT INTO project_skill (project_id, skill_id)
-- VALUES (2, 2);
