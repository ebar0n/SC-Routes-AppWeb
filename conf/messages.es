# Override default Play's validation messages

# --- Constraints
constraint.required=Obligatorio
constraint.min=Valor mínimo: {0}
constraint.max=Valor máximo: {0}
constraint.minLength=Longitud mínima: {0}
constraint.maxLength=Longitud máxima: {0}
constraint.email=Email

# --- Formats
format.date=Date (''{0}'')
format.numeric=Numérico
format.real=Real

# --- Errors
error.invalid=Valor incorrecto
error.required=Este campo es obligatorio
error.number=Se esperaba un valor numérico
error.real=Se esperaba un numero real
error.min=Debe ser mayor o igual que {0}
error.max=Debe ser menor o igual que {0}
error.minLength=La longitud mínima es de {0}
error.maxLength=La longitud máxima es de {0}
error.email=Se requiere un email válido
error.pattern=Debe satisfacer {0}

### --- play-authenticate START

# play-authenticate: Initial translations

playauthenticate.accounts.link.success=Cuenta enlazada correctamente
playauthenticate.accounts.merge.success=Cuentas unificadas correctamente

playauthenticate.verify_email.error.already_validated=Su email ya ha sido validado
playauthenticate.verify_email.error.set_email_first=Primero debe registrar un email.
playauthenticate.verify_email.message.instructions_sent=Las instrucciones para validar su cuenta han sido enviadas a {0}.
playauthenticate.verify_email.success=La dirección de email ({0}) ha sido verificada correctamente.

playauthenticate.reset_password.message.instructions_sent=Las instrucciones para restablecer su contraseña han sido enviadas a {0}.
playauthenticate.reset_password.message.email_not_verified=Su cuenta aún no ha sido validada. Se ha enviado un email incluyedo instrucciones para su validación. Intente restablecer la contraseña una vez lo haya recibido.
playauthenticate.reset_password.message.no_password_account=Su usuario todavía no ha sido configurado para utilizar contraseña.
playauthenticate.reset_password.message.success.auto_login=Su contraseña ha sido restablecida.
playauthenticate.reset_password.message.success.manual_login=Su contraseña ha sido restablecida. Intente volver a entrar utilizando su nueva contraseña.

playauthenticate.change_password.error.passwords_not_same=Las contraseñas no coinciden.
playauthenticate.change_password.success=La contraseña ha sido cambiada correctamente.

playauthenticate.password.signup.error.passwords_not_same=Las contraseñas no coinciden.
playauthenticate.password.login.unknown_user_or_pw=Usuario o contraseña incorrectos.

playauthenticate.password.verify_signup.subject=PlayAuthenticate: Complete su registro
playauthenticate.password.verify_email.subject=PlayAuthenticate: Confirme su dirección de email
playauthenticate.password.reset_email.subject=PlayAuthenticate: Cómo restablecer su contraseña

# play-authenticate: Additional translations

playauthenticate.login.email.placeholder=Su dirección de email
playauthenticate.login.password.placeholder=Elija una contraseña
playauthenticate.login.password.repeat=Repita la contraseña elegida
playauthenticate.login.title=Entrar
playauthenticate.login.password.placeholder=Contraseña
playauthenticate.login.now=Entrar
playauthenticate.login.forgot.password=¿Olvidó su contraseña?
playauthenticate.login.oauth=entre usando su cuenta con alguno de los siguientes proveedores:

playauthenticate.signup.title=Registrarse
playauthenticate.signup.name=Su nombre
playauthenticate.signup.now=Regístrese
playauthenticate.signup.oauth=regístrese usando su cuenta con alguno de los siguientes proveedores:

playauthenticate.verify.account.title=Es necesario validar su email
playauthenticate.verify.account.before=Antes de configurar una contraseña
playauthenticate.verify.account.first=valide su email

playauthenticate.change.password.title=Cambio de contraseña
playauthenticate.change.password.cta=Cambiar mi contraseña

playauthenticate.merge.accounts.title=Unir cuentas
playauthenticate.merge.accounts.question=¿Desea unir su cuenta ({0}) con su otra cuenta: {1}?
playauthenticate.merge.accounts.true=Sí, ¡une estas dos cuentas!
playauthenticate.merge.accounts.false=No, quiero abandonar esta sesión y entrar como otro usuario.
playauthenticate.merge.accounts.ok=OK

playauthenticate.link.account.title=Enlazar cuenta
playauthenticate.link.account.question=¿Enlazar ({0}) con su usuario?
playauthenticate.link.account.true=Sí, ¡enlaza esta cuenta!
playauthenticate.link.account.false=No, salir y crear un nuevo usuario con esta cuenta
playauthenticate.link.account.ok=OK

# play-authenticate: Signup folder translations

playauthenticate.verify.email.title=Verifique su email
playauthenticate.verify.email.requirement=Antes de usar PlayAuthenticate, debe validar su email.
playauthenticate.verify.email.cta=Se le ha enviado un email a la dirección registrada. Por favor, siga el link de este email para activar su cuenta.
playauthenticate.password.reset.title=Restablecer contraseña
playauthenticate.password.reset.cta=Restablecer mi contraseña

playauthenticate.password.forgot.title=Contraseña olvidada
playauthenticate.password.forgot.cta=Enviar instrucciones para restablecer la contraseña

playauthenticate.oauth.access.denied.title=Acceso denegado por OAuth
playauthenticate.oauth.access.denied.explanation=Si quiere usar PlayAuthenticate con OAuth, debe aceptar la conexión.
playauthenticate.oauth.access.denied.alternative=Si prefiere no hacerlo, puede también
playauthenticate.oauth.access.denied.alternative.cta=registrarse con un usuario y una contraseña.

playauthenticate.token.error.title=Error de token
playauthenticate.token.error.message=El token ha caducado o no existe.

playauthenticate.user.exists.title=El usuario existe
playauthenticate.user.exists.message=Otro usario ya está dado de alta con este identificador.

# play-authenticate: Navigation
playauthenticate.navigation.profile=Perfil
playauthenticate.navigation.link_more=Enlazar más proveedores
playauthenticate.navigation.logout=Salir
playauthenticate.navigation.login=Entrar
playauthenticate.navigation.home=Inicio
playauthenticate.navigation.restricted=Página restringida
playauthenticate.navigation.findrutas=Buscar rutas
playauthenticate.navigation.createroutes=Crear rutas
playauthenticate.navigation.support=Enviar sugerencias
playauthenticate.navigation.view_support=Ver sugerencias
playauthenticate.navigation.signup=Registrarse
playauthenticate.navigation.company=Compañias
playauthenticate.navigation.street=Calles
playauthenticate.navigation.suggestions=Enviar sugerencias
playauthenticate.navigation.suggestions_list=Sugerencias
playauthenticate.navigation.routescompany=Rutas
playauthenticate.navigation.routescompany_search=Buscar Rutas

# play-authenticate: Handler
playauthenticate.handler.loginfirst=Para ver ''{0}'', debe registrarse primero.

# play-authenticate: Profile
playauthenticate.profile.title=Perfil de usuario
playauthenticate.profile.mail=Su nombre es {0} y su dirección de mail es {1}!
playauthenticate.profile.unverified=sin validar - haga click para validar
playauthenticate.profile.verified=validada
playauthenticate.profile.providers_many=Hay {0} proveedores enlazados con su cuenta:
playauthenticate.profile.providers_one = Hay un proveedor enlazado con su cuenta:
playauthenticate.profile.logged=Ha entrado con:
playauthenticate.profile.session=Su ID de usuario es {0}. Su sesión expirará el {1}.
playauthenticate.profile.session_endless=Su ID de usuario es {0}. Su sesión no expirará nunca porque no tiene caducidad.
playauthenticate.profile.password_change=Cambie/establezca una contraseña para su cuenta

# play-authenticate - sample: Index page
playauthenticate.index.title=Rutas San Cristóbal
playauthenticate.index.intro=Bienvenido a Rutas San Cristóbal
playauthenticate.index.intro_2=En este sistema, usted podrá crear nuevas rutas o buscar rutas. Puede registrarse en la sección de "Registrarse"
playauthenticate.index.intro_3=Mire la barra de navegación superior para ver las opciones que tenemos disponibles.
playauthenticate.index.heading=Cabecera
playauthenticate.index.details=Ver detalles

# play-authenticate - sample: Restricted page
playauthenticate.restricted.secrets=¡Secretos y más secretos!

### --- play-authenticate END
