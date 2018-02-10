# requires pyjwt (https://pyjwt.readthedocs.io/en/latest/)
# pip install pyjwt


import datetime
import jwt


secret = """-----BEGIN PRIVATE KEY-----
MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQghLISgs5CcnaMernI
Eck4G5BScCroaGT1c4Ks0A/DWCKgCgYIKoZIzj0DAQehRANCAASCQfrm0j0Eeki+
agL8QpxPoYwl3iVQjpjUZdqbeadb7E4gJRgX5gdaoEH0V5Gthuv+Z0BEA4Fs9FQ1
tT3UUbHt
-----END PRIVATE KEY-----"""
keyId = "32G98MCS33"
teamId = "8EABX2KS59"
alg = 'ES256'

time_now = datetime.datetime.now()
time_expired = datetime.datetime.now() + datetime.timedelta(hours=12)

headers = {
	"alg": alg,
	"kid": keyId
}

payload = {
	"iss": teamId,
	"exp": int(time_expired.strftime("%s")),
	"iat": int(time_now.strftime("%s"))
}


if __name__ == "__main__":
	"""Create an auth token"""
	token = jwt.encode(payload, secret, algorithm=alg, headers=headers)

	print "----TOKEN----"
	print token

	print "----CURL----"
	print "curl -v -H 'Authorization: Bearer %s' \"https://api.music.apple.com/v1/catalog/us/artists/36954\" " % (token)
