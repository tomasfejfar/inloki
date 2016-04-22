using inloki.web.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace inloki.web.Controllers
{
	public class HomeController : Controller
	{
		public ActionResult Index()
		{
			ViewBag.Title = "Home Page";

			Database.Modify(d =>
			{
				d.Beacons.Add(new Beacon { Key = DateTime.Now.ToString() });
			});

			Database.Query(d =>
			{
				var xxx = from b in d.Beacons
						  select b.Key;

				ViewBag.Xxx = string.Join(", ", xxx);
			});

			return View();
		}
	}
}
