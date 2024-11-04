INSERT INTO public.papel(id, nome) VALUES (1, 'ADM');
INSERT INTO public.papel(id, nome) VALUES (2, 'PEDAGOGICO');
INSERT INTO public.papel(id, nome) VALUES (3, 'RECRUITER');
INSERT INTO public.papel(id, nome) VALUES (4, 'PROFESSOR');
INSERT INTO public.papel(id, nome) VALUES (5, 'ALUNO');

INSERT INTO public.usuario(email, nome, senha, id_papel) VALUES ('admin@mail.com', 'admin', '$2a$10$yLsrjeOc5hUYJKFxgO13XejuUi3AWR26MmFSF9JH57EFwMb/i7OVW', 1)
on conflict (email) do nothing;
