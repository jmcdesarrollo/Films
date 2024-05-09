-- Inserción de roles
INSERT IGNORE INTO `role` (`id`, `name`) VALUES ('1', 'ROLE_ADMIN'), ('2', 'ROLE_USER');
-- Inserción de usuarios
INSERT IGNORE INTO `user` (`id`, `active`, `birth_date`, `creation_date`, `email`, `image`, `last_login`, `name`, `password`, `surname`, `username`) VALUES ('1', b'1', '2023-11-01', '2023-11-01', 'admin@admin.com', NULL, NULL, 'admin', '$2a$10$6yaVSkmiLww5is6KV2XjoeOs57FdPZhP3FHgEe9KbfbcxNvKL9hP6', 'admin', 'tokioschool'), ('2', b'1', NULL, NULL, 'user@user.com', NULL, NULL, 'user', '$2a$10$p6S2lI20dtcZsjRktbrKQOHcc3FvVk7ntaEJHlkp2URMM2S3.4Kr6', NULL, 'user');
-- Asignación de roles a usuarios
INSERT IGNORE INTO `user_roles` (`user_Id`, `role_Id`) VALUES ('1', '1'), ('2', '2');