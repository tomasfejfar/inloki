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
				d.Beacons.Clear();

				d.Beacons.Add(new Beacon { id = "9apg", description = "Mall Entrance" });
				d.Beacons.Add(new Beacon { id = "iu48", description = "Escalator" });
				d.Beacons.Add(new Beacon { id = "xxd7", description = "Drug store entrance" });

				d.Paths.Clear();
				d.Paths.Add(new Path
				{
					name = "Drug store",
					description = "This will lead you to drug store.",
					waypoints = from b in d.Beacons
								select b.id
				});
			});

			Database.Query(d =>
			{
				var xxx = from b in d.Beacons
						  select b.id;

				ViewBag.Xxx = string.Join(", ", xxx);
			});

			return View();
		}

		[HttpGet]
		public JsonResult Something()
		{
			return Json(Database.Load().Beacons.First(), JsonRequestBehavior.AllowGet);
		}

	}
}
