using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace inloki.web.Controllers
{
	public class PingController : ApiController
	{
		// GET api/ping
		public IEnumerable<string> Get()
		{
			yield return "pong";
		}
	}
}
