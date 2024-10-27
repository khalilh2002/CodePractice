CREATE OR REPLACE FUNCTION insert_khalil_posts()
RETURNS VOID AS $$
BEGIN
  FOR i IN 1..10 LOOP
          INSERT INTO khalil_post (id, name)
          VALUES (i, 'khalil' || i);
  END LOOP;
END;
SELECT insert_khalil_posts();
