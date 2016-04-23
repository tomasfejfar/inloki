using inloki.web.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace inloki.web.Controllers
{
	public class AdminBeaconController : Controller
	{
		// GET: AdminBeacon
		public ActionResult Index()
		{
			return View(Database.Load().Beacons);
		}

		// GET: AdminBeacon/Details/5
		public ActionResult Details(string id)
		{
			return View(Database.Load().Beacons.Single(b => b.id == id));
		}

		// GET: AdminBeacon/Create
		public ActionResult Create()
		{
			return View();
		}

		// POST: AdminBeacon/Create
		[HttpPost]
		public ActionResult Create(FormCollection collection)
		{
			try
			{
				var beacon = new Beacon
				{
					id = collection["Id"],
					description = collection["Description"],
				};

				Database.Modify(d => d.Beacons.Add(beacon));

				return RedirectToAction("Index");
			}
			catch
			{
				return View();
			}
		}

		// GET: AdminBeacon/Edit/5
		public ActionResult Edit(string id)
		{
			return View(Database.Load().Beacons.Single(b => b.id == id));
		}

		// POST: AdminBeacon/Edit/5
		[HttpPost]
		public ActionResult Edit(string id, FormCollection collection)
		{
			try
			{
				Database.Modify(d =>
				{
					var beacon = d.Beacons.Single(b => b.id == id);

					d.Beacons.Remove(beacon);

					beacon = new Beacon
					{
						id = collection["Id"],
						description = collection["Description"],
					};

					d.Beacons.Add(beacon);
				});

				return RedirectToAction("Index");
			}
			catch
			{
				return View();
			}
		}

		// GET: AdminBeacon/Delete/5
		public ActionResult Delete(string id)
		{
			return View(Database.Load().Beacons.Single(b => b.id == id));
		}

		// POST: AdminBeacon/Delete/5
		[HttpPost]
		public ActionResult Delete(string id, FormCollection collection)
		{
			try
			{
				Database.Modify(d =>
				{
					var beacon = d.Beacons.Single(b => b.id == id);

					d.Beacons.Remove(beacon);
				});

				return RedirectToAction("Index");
			}
			catch
			{
				return View();
			}
		}
	}
}
