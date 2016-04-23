using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace inloki.web.Controllers
{
    public class BeaconsController : ApiController
    {
		// GET api/beacons/5
		public IEnumerable<string> Get(string id)
		{
			var d = Database.Load();

			return from b in d.Beacons
				   select b.id;
		}

	}
}
