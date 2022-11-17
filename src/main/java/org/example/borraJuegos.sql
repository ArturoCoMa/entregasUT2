CREATE OR REPLACE PROCEDURE public.borraJuegos(n int)
	LANGUAGE plpgsql
AS $procedure$
	BEGIN
		 DELETE FROM juego WHERE id = n;
	END;
$procedure$
;