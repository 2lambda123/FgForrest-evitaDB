session.createNewEntity("product")
	.setAttribute(
		"name", Locale.ENGLISH,
		"ASUS Vivobook 16 X1605EA-MB044W Indie Black"
	)
	.upsertVia(session);